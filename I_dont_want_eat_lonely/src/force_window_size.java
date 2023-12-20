import java.awt.*;
import java.awt.event.*;

public class force_window_size implements ComponentListener {
    private int window_width=100;
    private int window_height=100;
    public force_window_size(int _width, int _height){
        window_width=_width;
        window_height=_height;
    }

    @Override
    public void componentHidden(ComponentEvent e) {}

    @Override
    public void componentMoved(ComponentEvent e) {}

    @Override
    public void componentResized(ComponentEvent e) {
        // force window size
        Component wind = (Component)e.getSource();
        wind.setSize(window_width, window_height);
    }

    @Override
    public void componentShown(ComponentEvent e) {}
}
