package org.utkarsh.taskmanager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.utkarsh.taskmanager.model.Task;
import tools.jackson.databind.json.JsonMapper;

@RestController
@RequestMapping("/debug/jackson")
public class Tester {

    private final JsonMapper jsonMapper;

    public Tester(JsonMapper jsonMapper) {
        this.jsonMapper = jsonMapper;
    }

    /*
     * Test 1:
     * Show which Jackson mapper Spring Boot is providing.
     */
    @GetMapping("/mapper")
    public String mapper() {

        System.out.println("======================================");
        System.out.println("Jackson mapper class:");
        System.out.println(jsonMapper.getClass().getName());

        System.out.println("--------------------------------------");
        System.out.println("JsonMapper:");
        System.out.println(jsonMapper);

        System.out.println("======================================");

        return "Check console";
    }

    /*
     * Test 2:
     * Directly convert JSON -> Task.
     *
     * This completely bypasses your HTTP @RequestBody handling,
     * controller parameter binding, service and JPA.
     */
    @GetMapping("/deserialize")
    public String deserialize() {

        String json = """
                {
                  "title": "Prepare sprint review",
                  "description": "Review completed tasks, summarize blockers, and share demo progress with the team.",
                  "priority": true,
                  "dueDate": "2026-09-15T18:00:00.000Z",
                  "createdAt": "2026-09-01T20:43:36.851Z",
                  "updatedAt": "2026-09-01T20:43:36.851Z"
                }
                """;

        try {

            Task task = jsonMapper.readValue(json, Task.class);

            System.out.println("======================================");
            System.out.println("JSON:");
            System.out.println(json);

            System.out.println("--------------------------------------");
            System.out.println("Task after Jackson deserialization:");
            System.out.println(task);

            System.out.println("======================================");

            return task.toString();

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR: " + e.getMessage();
        }
    }

    /*
     * Test 3:
     * Java object -> JSON.
     *
     * This tells us what Jackson sees for serialization.
     */
    @GetMapping("/serialize")
    public String serialize() {

        Task task = new Task();

        try {

            String json = jsonMapper.writeValueAsString(task);

            System.out.println("======================================");
            System.out.println("Task:");
            System.out.println(task);

            System.out.println("--------------------------------------");
            System.out.println("JSON produced by Jackson:");
            System.out.println(json);

            System.out.println("======================================");

            return json;

        } catch (Exception e) {

            e.printStackTrace();

            return "ERROR: " + e.getMessage();
        }
    }
}