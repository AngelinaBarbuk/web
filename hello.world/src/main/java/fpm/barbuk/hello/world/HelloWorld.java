package fpm.barbuk.hello.world;

import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

/**
 * Hello world!
 *
 */
public class HelloWorld extends AbstractVerticle {

	private static final int PORT = 8080;
	private static final String MESSAGE = "<h1>Hello from my first Vert.x application</h1>";

	public void start(Future fut) {
		Router router = Router.router(vertx);
		router.route().handler(BodyHandler.create());
		router.get("/hello").handler(this::handleGetHello);

		vertx.createHttpServer().requestHandler(router::accept).listen(PORT, result -> {
			if (result.succeeded()) {
				fut.complete();
			} else {
				fut.fail(result.cause());
			}
		});

	}

	private void handleGetHello(RoutingContext routingContext) {
		HttpServerResponse response = routingContext.response();
		response.end(MESSAGE);
	}
}
