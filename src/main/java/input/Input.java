package input;

import org.joml.AxisAngle4d;
import org.joml.AxisAngle4f;

import static org.lwjgl.glfw.GLFW.*;



public class Input {
    private static final AxisAngle4f currentPos = new AxisAngle4f();
    private static boolean inWindow = false;
    private static boolean leftButtonPressed = false;
    private static boolean rightButtonPressed = false;


    public static AxisAngle4f getCurrentPos() {
        return currentPos;
    }

    public static boolean isInWindow() {
        return inWindow;
    }

    public static boolean isLeftButtonPressed() {
        return leftButtonPressed;
    }

    public static boolean isRightButtonPressed() {
        return rightButtonPressed;
    }

    public static void initInput(long window){
        glfwSetCursorPosCallback(window, (windowHandle, xpos, ypos) -> {
            currentPos.x = (float) xpos;
            currentPos.y = (float) ypos;
        });
        glfwSetCursorEnterCallback(window, (windowHandle, entered) -> {
            inWindow = entered;
        });
        glfwSetMouseButtonCallback(window, (windowHandle, button, action, mode) -> {
            leftButtonPressed = button == GLFW_MOUSE_BUTTON_1 && action == GLFW_PRESS;
            rightButtonPressed = button == GLFW_MOUSE_BUTTON_2 && action == GLFW_PRESS;
        });


    }
}
