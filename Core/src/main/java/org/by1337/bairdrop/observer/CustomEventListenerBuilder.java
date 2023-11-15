package org.by1337.bairdrop.observer;

import org.by1337.bairdrop.observer.event.CustomEvent;
import org.by1337.bairdrop.observer.requirement.Requirements;
import org.by1337.api.util.NameKey;
import org.by1337.bairdrop.observer.requirement.condition.Condition;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

/**
 * The CustomEventListenerBuilder class is responsible for constructing instances of CustomEventListener with various parameters.
 * It provides a fluent interface for configuring the properties of a custom event listener.
 */
public class CustomEventListenerBuilder {
    private CustomEvent customEvent;
    private String description;
    private String[] commands;
    private String[] denyCommands;
    private Requirements requirements;
    private Condition condition;
    private NameKey nameKey;


    /**
     * Sets the custom event for the custom event listener being built.
     *
     * @param customEvent The custom event to associate with the listener.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder customEvent(@NotNull CustomEvent customEvent) {
        this.customEvent = customEvent;
        return this;
    }

    /**
     * Sets the description for the custom event listener.
     *
     * @param description The description of the listener.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder description(@NotNull String description) {
        this.description = description;
        return this;
    }

    /**
     * Sets the available commands for the custom event listener as an array of strings.
     *
     * @param commands An array of available commands.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder commands(@NotNull String[] commands) {
        this.commands = commands;
        return this;
    }

    /**
     * Sets the available commands for the custom event listener from a list of strings.
     *
     * @param commands A list of available commands.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder commands(@NotNull List<String> commands) {
        if (commands != null) {
            this.commands = commands.toArray(new String[0]);
        }
        return this;
    }


    /**
     * Sets the denied commands for the custom event listener as an array of strings.
     *
     * @param denyCommands An array of denied commands.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder denyCommands(@NotNull List<String> denyCommands) {
        if (denyCommands != null) {
            this.denyCommands = denyCommands.toArray(new String[0]);
        }
        return this;
    }

    /**
     * Sets the denied commands for the custom event listener from a list of strings.
     *
     * @param denyCommands A list of denied commands.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder denyCommands(@NotNull String[] denyCommands) {
        this.denyCommands = denyCommands;
        return this;
    }

    /**
     * Sets the requirements for the custom event listener.
     *
     * @param requirements The requirements for the listener.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder requirements(@NotNull Requirements requirements) {
        this.requirements = requirements;
        return this;
    }

    /**
     * Sets the condition for the custom event listener.
     *
     * @param condition The condition to be met for the listener to trigger.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder condition(@NotNull Condition condition) {
        this.condition = condition;
        return this;
    }

    /**
     * Sets the name key for the custom event listener.
     *
     * @param nameKey The NameKey to identify the listener.
     * @return This CustomEventListenerBuilder instance for method chaining.
     */
    @Contract("_ -> this")
    public CustomEventListenerBuilder nameKey(@NotNull NameKey nameKey) {
        this.nameKey = nameKey;
        return this;
    }
    /**
     * Constructs a new CustomEventListener using the configured parameters.
     *
     * @return A new CustomEventListener instance.
     */
    public CustomEventListener build() {
        return new CustomEventListener(this.customEvent, this.description, this.commands, this.denyCommands, this.requirements, this.condition, this.nameKey);
    }

}
