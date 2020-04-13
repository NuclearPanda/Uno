package Game;

public class Card {
    private Color color;
    private String value;

    public Card(Color color, String value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (color != card.color) return false;
        return value.equals(card.value);
    }

    @Override
    public int hashCode() {
        int result = color.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    public boolean matches(Card otherCard) {
        if (color == Color.BLACK) {
            return true;
        }
        return color == otherCard.color || value.equals(otherCard.value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        switch (color) {
            case RED:
                sb.append("Red ");
                break;
            case BLUE:
                sb.append("Blue ");
                break;
            case BLACK:
                sb.append("Black ");
                break;
            case GREEN:
                sb.append("Green ");
                break;
            case YELLOW:
                sb.append("Yellow ");
        }
        sb.append(value);
        return sb.toString();
    }
}
