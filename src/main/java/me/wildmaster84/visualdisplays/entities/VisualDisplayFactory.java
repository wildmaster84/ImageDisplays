package me.wildmaster84.visualdisplays.entities;

import me.wildmaster84.visualdisplays.entities.terminal.BlockTerminal;
import me.wildmaster84.visualdisplays.entities.terminal.DisplayTerminal;
import me.wildmaster84.visualdisplays.util.ColorConversor;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.TextDisplay;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;
import org.joml.Vector3f;

import java.util.LinkedList;
import java.util.Objects;

public class VisualDisplayFactory {
    private static VisualDisplayFactory instance;

    public static VisualDisplayFactory getInstance() {
        if (instance == null) {
            instance = new VisualDisplayFactory();
        }
        return instance;
    }

    /**
     * Creates a BlockTerminal with default settings.
     * Entities are not persistent.
     *
     * @param at Block to create the terminal at. Must be a solid block.
     * @param facing BlockFace to determine the orientation of the terminal.
     *               Must be NORTH, EAST, SOUTH or WEST.
     * @return BlockTerminal
     */
    @NotNull
    public BlockTerminal createBlockTerminal(@NotNull Block at,
                                             @NotNull BlockFace facing){
        return createBlockTerminal(
                at,
                facing,
                18,
                12,
                Color.BLACK,
                ColorConversor.getInstance().toBukkitColor(ChatColor.DARK_GREEN.getColor()),
                -0.05f,
                0,
                0.325f);
    }

    /**
     * Creates a BlockTerminal with the specified line limit and background color.
     * Entities are not persistent.
     *
     * @param at Block to create the terminal at. Must be a solid block.
     * @param facing BlockFace to determine the orientation of the terminal.
     *               Must be NORTH, EAST, SOUTH or WEST.
     * @param lineLimit Maximum number of lines that can be displayed
     * @param backgroundColor Background color of the terminal
     * @param translationX X translation of the text display
     * @param translationY Y translation of the text display
     * @param scale Scale of the text display
     * @return BlockTerminal
     */
    @NotNull
    public BlockTerminal createBlockTerminal(@NotNull Block at,
                                             @NotNull BlockFace facing,
                                             int lineLimit,
                                             int maxLines,
                                             @NotNull Color backgroundColor,
                                             @NotNull Color defaultTextColor,
                                             float translationX,
                                             float translationY,
                                             float scale){
        Objects.requireNonNull(at, "'at' cannot be null");
        Objects.requireNonNull(facing, "'facing' cannot be null");
        if (!at.getType().isSolid())
            throw new IllegalArgumentException("Block at " + at.getLocation() + " is not solid");
        switch (facing){
            case NORTH -> {
                Vector add = new Vector(0.5, 0.0, 1.005);
                Location location = at.getLocation().clone().add(add);
                TextDisplay textDisplay = location.getWorld().spawn(location, TextDisplay.class);
                textDisplay.setAlignment(TextDisplay.TextAlignment.LEFT);
                Transformation transformation = textDisplay.getTransformation();
                textDisplay.setTransformation(new Transformation(
                        new Vector3f(translationX, translationY, 0),
                        transformation.getLeftRotation(),
                        new Vector3f(scale),
                        transformation.getRightRotation()));
                textDisplay.setBackgroundColor(Color.BLACK);
                textDisplay.setPersistent(false);
                return new BlockTerminal(
                        at,
                        textDisplay,
                        new LinkedList<>(),
                        lineLimit,
                        maxLines,
                        backgroundColor,
                        defaultTextColor);
            }
            case EAST -> {
                Vector add = new Vector(-0.005, 0.0, 0.5);
                Location location = at.getLocation().clone().add(add);
                TextDisplay textDisplay = location.getWorld().spawn(location, TextDisplay.class);
                textDisplay.setAlignment(TextDisplay.TextAlignment.LEFT);
                Transformation transformation = textDisplay.getTransformation();
                textDisplay.setTransformation(new Transformation(
                        new Vector3f(translationX, translationY, 0),
                        transformation.getLeftRotation(),
                        new Vector3f(scale),
                        transformation.getRightRotation()));
                textDisplay.setBackgroundColor(Color.BLACK);
                textDisplay.setPersistent(false);
                textDisplay.setRotation(90f, 0f);
                return new BlockTerminal(
                        at,
                        textDisplay,
                        new LinkedList<>(),
                        lineLimit,
                        maxLines,
                        backgroundColor,
                        defaultTextColor);
            }
            case SOUTH -> {
                Vector add = new Vector(0.5, 0.0, -0.005);
                Location location = at.getLocation().clone().add(add);
                TextDisplay textDisplay = location.getWorld().spawn(location, TextDisplay.class);
                textDisplay.setAlignment(TextDisplay.TextAlignment.LEFT);
                Transformation transformation = textDisplay.getTransformation();
                textDisplay.setTransformation(new Transformation(
                        new Vector3f(translationX, translationY, 0),
                        transformation.getLeftRotation(),
                        new Vector3f(scale),
                        transformation.getRightRotation()));
                textDisplay.setBackgroundColor(Color.BLACK);
                textDisplay.setPersistent(false);
                textDisplay.setRotation(180f, 0f);
                return new BlockTerminal(
                        at,
                        textDisplay,
                        new LinkedList<>(),
                        lineLimit,
                        maxLines,
                        backgroundColor,
                        defaultTextColor);
            }
            case WEST -> {
                Vector add = new Vector(1.005, 0.0, 0.5);
                Location location = at.getLocation().clone().add(add);
                TextDisplay textDisplay = location.getWorld().spawn(location, TextDisplay.class);
                textDisplay.setAlignment(TextDisplay.TextAlignment.LEFT);
                Transformation transformation = textDisplay.getTransformation();
                textDisplay.setTransformation(new Transformation(
                        new Vector3f(translationX, translationY, 0),
                        transformation.getLeftRotation(),
                        new Vector3f(scale),
                        transformation.getRightRotation()));
                textDisplay.setBackgroundColor(Color.BLACK);
                textDisplay.setPersistent(false);
                textDisplay.setRotation(-90f, 0f);
                return new BlockTerminal(
                        at,
                        textDisplay,
                        new LinkedList<>(),
                        lineLimit,
                        maxLines,
                        backgroundColor,
                        defaultTextColor);
            }
            default -> {
                throw new IllegalArgumentException("Unsupported facing: " + facing);
            }
        }
    }

    /**
     * Creates a DisplayTerminal with default settings.
     *
     * @param at Location to create the terminal at.
     * @return DisplayTerminal
     */
    @NotNull
    public DisplayTerminal createDisplayTerminal(@NotNull Location at){
        return createDisplayTerminal(
                at,
                18,
                12,
                Color.BLACK,
                ColorConversor.getInstance().toBukkitColor(ChatColor.DARK_GREEN.getColor()),
                -0.05f,
                0,
                0.325f);
    }

    /**
     * Creates a DisplayTerminal.
     *
     * @param at Location to create the terminal at.
     * @param lineLimit Maximum number of characters per line
     * @param maxLines Maximum number of lines that can be displayed
     * @param backgroundColor Background color of the terminal
     * @param defaultTextColor Default text color
     * @param translationX X translation of the text display
     * @param translationY Y translation of the text display
     * @param scale Scale of the text display
     * @return DisplayTerminal
     */
    @NotNull
    public DisplayTerminal createDisplayTerminal(@NotNull Location at,
                                               int lineLimit,
                                               int maxLines,
                                               @NotNull Color backgroundColor,
                                               @NotNull Color defaultTextColor,
                                               float translationX,
                                               float translationY,
                                               float scale){
        Objects.requireNonNull(at, "'at' cannot be null");
        TextDisplay textDisplay = at.getWorld().spawn(at, TextDisplay.class);
        textDisplay.setAlignment(TextDisplay.TextAlignment.LEFT);
        Transformation transformation = textDisplay.getTransformation();
        textDisplay.setTransformation(new Transformation(
                new Vector3f(translationX, translationY, 0),
                transformation.getLeftRotation(),
                new Vector3f(scale),
                transformation.getRightRotation()));
        textDisplay.setBackgroundColor(Color.BLACK);
        textDisplay.setPersistent(false);
        return new DisplayTerminal(
                textDisplay,
                new LinkedList<>(),
                lineLimit,
                maxLines,
                backgroundColor,
                defaultTextColor);
    }
}
