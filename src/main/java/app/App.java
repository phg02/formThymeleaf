package app;

import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;

/**
 * Main Application Class.
 * <p>
 * Running this class as regular java application will start the
 * Javalin HTTP Server and our web application.
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class App {

    public static final int JAVALIN_PORT = 7001;
    public static final String CSS_DIR = "css/";
    public static final String IMAGES_DIR = "images/";
    public static final String FONT_DIR = "fonts/";

    public static void main(String[] args) {
        // Create our HTTP server and listen in port 7000
        Javalin app = Javalin.create(config -> {
            config.registerPlugin(new RouteOverviewPlugin("/help/routes"));

            // Uncomment this if you have files in the CSS Directory
            config.addStaticFiles(CSS_DIR);

            // Uncomment this if you have files in the Images Directory
            config.addStaticFiles(IMAGES_DIR);

            // Uncomment this if you have files in the Font Directory
            config.addStaticFiles(FONT_DIR);
        }).start(JAVALIN_PORT);

        // Configure Web Routes
        configureRoutes(app);
    }

    public static void configureRoutes(Javalin app) {
        // All webpages are listed here as GET pages
        app.get(PageIndex.URL, new PageIndex());
        app.get(PageMission.URL, new PageMission());
        app.get(PageST2A.URL, new PageST2A());
        app.get(PageST2B.URL, new PageST2B());
        app.get(statecity.URL, new statecity());
        app.get(PageST3A.URL, new PageST3A());
        app.get(PageST3B.URL, new PageST3B());
        app.get(pagesubB.URL, new pagesubB());
        app.get(PageWorld.URL, new PageWorld());

        // Add / uncomment POST commands for any pages that need web form POSTS
        // app.post(PageIndex.URL, new PageIndex());
        // app.post(PageMission.URL, new PageMission());
        app.post("/selectyear", ctx -> {
            ctx.sessionAttribute("startyear", ctx.formParam("startyear"));
            ctx.sessionAttribute("endyear", ctx.formParam("endyear"));
            ctx.redirect("/world.html");
        });
        app.post("/sort", ctx -> {
            ctx.sessionAttribute("sort", ctx.formParam("sortby"));
            ctx.redirect("/world.html");
        });
        app.post("/selectStuff", ctx -> {
            ctx.sessionAttribute("country", ctx.formParam("country"));
            ctx.sessionAttribute("startyear1", ctx.formParam("startYear"));
            ctx.sessionAttribute("endyear1", ctx.formParam("endYear"));
            ctx.redirect("/country.html");
        });
        app.post("/filter", ctx -> {
            ctx.sessionAttribute("sort", ctx.formParam("sortBy"));
            ctx.sessionAttribute("variable", ctx.formParam("variable"));
            ctx.redirect("/country.html");
        });

        app.post(statecity.URL, new statecity());
        // app.post(PageST3A.URL, new PageST3A());
        // app.post(PageST3B.URL, new PageST3B());
    }

}
