package com.elegantbanshee;

import com.elegantbanshee.data.Constants;
import com.elegantbanshee.util.Logger;
import com.elegantbanshee.ElegantBansheeComServer;

import java.util.logging.Level;

import static spark.Spark.*;

public class ElegantBansheeCom {
	
	public static void main(String[] args) {
		// Init logger
        Logger.setLevel(Level.parse("INFO"));
        Logger.info("Starting Twitched %s", Constants.VERSION);
        // Parse port
        int port = 5000;
        String portString = System.getenv("PORT");
        try {
            if (portString != null && !portString.isEmpty())
                port = Integer.parseInt(portString);
        }
        catch (NumberFormatException e) {
            Logger.warn("Failed to parse PORT env var: %s", portString);
        }
        // Set values
        port(port);
        staticFiles.location("/static/");
        staticFiles.expireTime(604800); // One Week cache
        // Web
        ElegantBansheeComServer.getGeneric("/", "index.hbs");
        ElegantBansheeComServer.getGeneric("/post/initial_post","post_initial_post.hbs");
        ElegantBansheeComServer.getGeneric("/post/a_foot","post_a_foot.hbs");
        ElegantBansheeComServer.getGeneric("/post/an_actual_post", "post_an_actual_post.hbs");
	}
	
	/**
     * Get an environment variable or log and die
     * @param name env var
     */
    private static String getenv(String name) {
        String env = System.getenv(name);
        if (env == null || env.isEmpty()) {
            Logger.warn("Missing required environment variable: %s", name);
            System.exit(1);
        }
        return env;
    }
}
