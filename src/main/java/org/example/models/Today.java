package org.example.models;

import java.util.List;

public record Today(String color, String date, String entry_title, List<String> extra, Readings readings) {
}
