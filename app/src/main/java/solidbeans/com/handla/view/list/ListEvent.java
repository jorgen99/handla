package solidbeans.com.handla.view.list;

class ListEvent {
    private final int position;

    ListEvent(int position) {
        this.position = position;
    }

    int getPosition() {
        return position;
    }
}
