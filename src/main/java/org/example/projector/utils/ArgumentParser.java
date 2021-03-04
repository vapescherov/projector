package org.example.projector.utils;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toMap;

public class ArgumentParser {

    private final Map<String, String> args;

    public ArgumentParser(String[] args) {
        this.args = Arrays.stream(args)
                .map(arg -> arg.split("="))
                .collect(toMap(split -> split[0], split -> split.length > 1 ? split[1] : ""));
    }

    public String getAsString(String... flags) {
        return Arrays.stream(flags)
                .map(args::get)
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(() -> new NoSuchArgumentException(flags));
    }

}
