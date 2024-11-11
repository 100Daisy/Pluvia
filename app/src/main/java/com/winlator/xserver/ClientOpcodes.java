package com.winlator.xserver;

public abstract class ClientOpcodes {
    public static final byte CREATE_WINDOW = 1;
    public static final byte CHANGE_WINDOW_ATTRIBUTES = 2;
    public static final byte GET_WINDOW_ATTRIBUTES = 3;
    public static final byte DESTROY_WINDOW = 4;
    public static final byte REPARENT_WINDOW = 7;
    public static final byte MAP_WINDOW = 8;
    public static final byte UNMAP_WINDOW = 10;
    public static final byte CONFIGURE_WINDOW = 12;
    public static final byte GET_GEOMETRY = 14;
    public static final byte QUERY_TREE = 15;
    public static final byte INTERN_ATOM = 16;
    public static final byte CHANGE_PROPERTY = 18;
    public static final byte DELETE_PROPERTY = 19;
    public static final byte GET_PROPERTY = 20;
    public static final byte SET_SELECTION_OWNER = 22;
    public static final byte GET_SELECTION_OWNER = 23;
    public static final byte SEND_EVENT = 25;
    public static final byte GRAB_POINTER = 26;
    public static final byte UNGRAB_POINTER = 27;
    public static final byte QUERY_POINTER = 38;
    public static final byte TRANSLATE_COORDINATES = 40;
    public static final byte WARP_POINTER = 41;
    public static final byte SET_INPUT_FOCUS = 42;
    public static final byte GET_INPUT_FOCUS = 43;
    public static final byte OPEN_FONT = 45;
    public static final byte LIST_FONTS = 49;
    public static final byte CREATE_PIXMAP = 53;
    public static final byte FREE_PIXMAP = 54;
    public static final byte CREATE_GC = 55;
    public static final byte CHANGE_GC = 56;
    public static final byte SET_CLIP_RECTANGLES = 59;
    public static final byte FREE_GC = 60;
    public static final byte COPY_AREA = 62;
    public static final byte POLY_LINE = 65;
    public static final byte POLY_SEGMENT = 66;
    public static final byte POLY_RECTANGLE = 67;
    public static final byte POLY_FILL_RECTANGLE = 70;
    public static final byte PUT_IMAGE = 72;
    public static final byte GET_IMAGE = 73;
    public static final byte CREATE_COLORMAP = 78;
    public static final byte FREE_COLORMAP = 79;
    public static final byte CREATE_CURSOR = 93;
    public static final byte CREATE_GLYPH_CURSOR = 94;
    public static final byte FREE_CURSOR = 95;
    public static final byte QUERY_EXTENSION = 98;
    public static final byte GET_KEYBOARD_MAPPING = 101;
    public static final byte BELL = 104;
    public static final byte SET_SCREEN_SAVER = 107;
    public static final byte GET_SCREEN_SAVER = 108;
    public static final byte FORCE_SCREEN_SAVER = 115;
    public static final byte GET_MODIFIER_MAPPING = 119;
    public static final byte NO_OPERATION = 127;
}