package dev.gothaj.utilities.ui;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    private double x;
    private double y;
    private double width;
    private double height;

    public boolean isInside(double mouseX, double mouseY) {
        return mouseX < x+width && mouseX > x && mouseY < y+height && mouseY > y;
    }
}
