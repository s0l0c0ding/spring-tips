package dev.solocoding.jsonhibernate;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Postdto
 */
public record Postdto(String post, JsonNode comment) {
}