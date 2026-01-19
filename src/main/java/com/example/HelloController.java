package com.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.annotation.JsonProperty;

@RestController
public class HelloController {

    @RequestMapping("/")
    String hello() {
        return "Hello World!";
    }

    static class Result {
        @JsonProperty("left")
        private final int left;

        @JsonProperty("right")
        private final int right;

        @JsonProperty("answer")
        private final long answer;

        public Result(int left, int right, long answer) {
            this.left = left;
            this.right = right;
            this.answer = answer;
        }

        public int getLeft() {
            return left;
        }

        public int getRight() {
            return right;
        }

        public long getAnswer() {
            return answer;
        }
    }

    // SQL sample
    @RequestMapping("calc")
    Result calc(@RequestParam int left, @RequestParam int right) {
        // Return a hardcoded result instead of querying the database
        long answer = left + right;
        return new Result(left, right, answer);
    }
}
