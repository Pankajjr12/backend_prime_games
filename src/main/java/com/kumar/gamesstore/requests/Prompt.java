package com.kumar.gamesstore.requests;

public class Prompt {

    private String prompt;

    // No-arg constructor
    public Prompt() {
    }

    // All-args constructor
    public Prompt(String prompt) {
        this.prompt = prompt;
    }

    // Getter and setter for the 'prompt' field
    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    @Override
    public String toString() {
        return "Prompt{"
                + "prompt='" + prompt + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Prompt)) {
            return false;
        }

        Prompt prompt1 = (Prompt) o;

        return prompt != null ? prompt.equals(prompt1.prompt) : prompt1.prompt == null;
    }

    @Override
    public int hashCode() {
        return prompt != null ? prompt.hashCode() : 0;
    }
}
