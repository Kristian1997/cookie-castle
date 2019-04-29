package dk.sdu.cookie.castle.map.entities.door;

import dk.sdu.cookie.castle.common.data.Entityparts.PositionPart;

public enum DoorPosition {
    TOP(),
    RIGHT(),
    BOTTOM(),
    LEFT();

    private PositionPart positionPart;

    DoorPosition() {
        positionPart = new PositionPart(0, 0, 0);
    }

    public PositionPart getPositionPart() {
        return positionPart;
    }

    /**
     * Set the possible centered door locations and account for game area margin
     *
     * @param displayWidth  Display pixel width
     * @param displayHeight Display pixel height
     */
    public void setPosition(int displayWidth, int displayHeight) {
        int margin = 150; // This is the margin in pixels from the edges
        switch (this) {
            case TOP:
                positionPart.setX(displayWidth / 2);
                positionPart.setY(displayHeight - margin);
                positionPart.setRadians(0);
                break;
            case RIGHT:
                positionPart.setX(displayWidth - margin);
                positionPart.setY(displayHeight / 2);
                positionPart.setRadians(1.5708f);
                break;
            case BOTTOM:
                positionPart.setX(displayWidth / 2);
                positionPart.setY(margin);
                positionPart.setRadians(3.14159f);
                break;
            case LEFT:
                positionPart.setX(margin);
                positionPart.setY(displayHeight / 2);
                positionPart.setRadians(4.71239f);
                break;
        }
    }
}