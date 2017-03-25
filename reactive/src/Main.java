import com.google.gson.Gson;
import io.netty.buffer.ByteBuf;
import io.netty.channel.DefaultFileRegion;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.RxNetty;
import io.reactivex.netty.protocol.http.server.HttpServer;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import rx.Observable;

import java.io.File;
import java.nio.charset.Charset;

public class Main {
    private static Gson gson = new Gson();
    private static MongoDB mongoDB = new MongoDB();

    private static Observable<Void> postRegisterRoute(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        return request.getContent()
                .map(byteBuf -> byteBuf.toString(Charset.defaultCharset()))
                .reduce("", (s, s2) -> s + s2)
                .map(s -> gson.fromJson(s, User.class))
                .flatMap(user -> {
                    if (user == null) {
                        response.setStatus(HttpResponseStatus.BAD_REQUEST);
                        response.writeString("Failed");
                        return response.close();
                    }
                    return mongoDB.registerUser(user)
                            .flatMap(success -> {
                                response.setStatus(HttpResponseStatus.OK);
                                return response.close();
                            });
                });
    }

    private static Observable<Void> getUsersRoute(HttpServerResponse<ByteBuf> response) {
        return Observable.defer(() -> mongoDB.getUsers()
                .doOnNext(user -> response.writeString(user.toString() + "\n"))
                .doOnCompleted(() -> response.setStatus(HttpResponseStatus.OK))
                .lastOrDefault(null)
                .flatMap(user -> response.close()));
    }

    private static Observable<Void> getProductsRoute(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        String email = request.getHeaders().get("x-email");
        System.out.println("email: " + email);
        return Observable.defer(() -> mongoDB.getProductsForUser(email)
                .toList()
                .single()
                .doOnNext(list -> {
                    response.writeString(gson.toJson(list));
                    response.setStatus(HttpResponseStatus.OK);
                })
                .flatMap(l -> response.close()));
    }

    private static Observable<Void> postProductsRoute(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {
        return request.getContent()
                .map(byteBuf -> byteBuf.toString(Charset.defaultCharset()))
                .reduce("", (s1, s2) -> s1 + s2)
                .map(s -> gson.fromJson(s, Product.class))
                .flatMap(product -> {
                    if (product == null) {
                        response.setStatus(HttpResponseStatus.BAD_REQUEST);
                        response.writeString("Failed");
                        return response.close();
                    }
                    return mongoDB.addProduct(product)
                            .flatMap(success -> {
                                response.setStatus(HttpResponseStatus.OK);
                                return response.close();
                            });
                });
    }

    private static HttpServer startServer() throws InterruptedException {
        return RxNetty.createHttpServer(3535, (request, response) -> {
            HttpMethod method = request.getHttpMethod();
            String path = request.getPath();
            System.out.println(method + " " + path + " on " + Thread.currentThread().getName());
            if (method.equals(HttpMethod.GET) && path.equals("/")) {
                return request.getContent().flatMap(bb -> {
                    File file = new File("src/User.java");
                    System.out.println("file " + file.exists());
                    response.setStatus(HttpResponseStatus.OK);
                    response.getHeaders().setContentLength(file.length());
                    response.getHeaders().setHeader("Content-Disposition", "attachment;filename=" + file.getName());
                    response.writeFileRegion(new DefaultFileRegion(file, 0, file.length()));
                    return response.close();
                });
            } else if (method.equals(HttpMethod.POST) && path.equals("/register")) {
                return postRegisterRoute(request, response);
            } else if (method.equals(HttpMethod.GET) && path.equals("/users")) {
                return getUsersRoute(response);
            } else if (method.equals(HttpMethod.GET) && path.equals("/products")) {
                return getProductsRoute(request, response);
            } else if (method.equals(HttpMethod.POST) && path.equals("/products")) {
                return postProductsRoute(request, response);
            }
            return null;
        }).withErrorHandler(throwable -> {
            throwable.printStackTrace();
            return Observable.error(throwable);
        }).withErrorResponseGenerator((httpServerResponse, throwable) -> {
            throwable.printStackTrace();
            httpServerResponse.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
            httpServerResponse.close();
        }).start();
    }

    public static void main(String[] args) throws InterruptedException {
        HttpServer server = startServer();
        server.waitTillShutdown();
    }
}
