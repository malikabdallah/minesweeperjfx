package application.view;

import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CustomImage extends ImageView{
    public int getC() {
        return c;
    }

    public void setC(int c) {
        this.c = c;
    }

    private int c;

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    private int r;

    public boolean isFlag() {
        return isFlag;
    }

    public void setFlag(boolean flag) {
        isFlag = flag;
    }

    private boolean isFlag;

    public CustomImage(int c, int r, String url){
        super(url);
        this.c=c;
        this.r=r;
        this.isFlag = false;
    }


}
