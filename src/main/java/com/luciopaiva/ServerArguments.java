package com.luciopaiva;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import static com.luciopaiva.Constants.SELECT_TIMEOUT_IN_MILLIS;

class ServerArguments {

    private static final Options options = new Options();

    static {
        options.addOption("p", "port", true, "the server port");
        options.addOption("w", "wait", true, "wait time between select()s, in millis");
    }

    int port = Constants.SERVER_PORT;
    int selectTimeoutInMillis = SELECT_TIMEOUT_IN_MILLIS;

    static ServerArguments parse(String ...args) {
        CommandLineParser parser = new DefaultParser();
        ServerArguments arguments = new ServerArguments();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("p")) {
                arguments.port = Integer.parseInt(cmd.getOptionValue("p"));
            }
            if (cmd.hasOption("w")) {
                arguments.selectTimeoutInMillis = Integer.parseInt(cmd.getOptionValue("w"));
            }
        } catch (ParseException ignored) {}

        return arguments;
    }
}
