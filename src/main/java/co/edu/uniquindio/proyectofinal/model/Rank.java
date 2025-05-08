package co.edu.uniquindio.proyectofinal.model;

public enum Rank {
    BRONZE(0, 500, "Bronce"),
    SILVER(501, 1000, "Plata"),
    GOLD(1001, 5000, "Oro"),
    PLATINUM(5001, Integer.MAX_VALUE, "Platino");

    private final int minPoints;
    private final int maxPoints;
    private final String displayName;

    Rank(int minPoints, int maxPoints, String displayName) {
        this.minPoints = minPoints;
        this.maxPoints = maxPoints;
        this.displayName = displayName;
    }

    /**
     * Determina el rango basado en los puntos proporcionados
     * @param points La cantidad de puntos del cliente
     * @return El rango correspondiente a la cantidad de puntos
     */
    public static Rank getRankByPoints(int points) {
        for (Rank rank : Rank.values()) {
            if (points >= rank.minPoints && points <= rank.maxPoints) {
                return rank;
            }
        }
        // Si por alguna razón no se encontró un rango (aunque esto no debería ocurrir)
        return BRONZE; // Por defecto, devuelve BRONZE
    }

    /**
     * Obtiene el rango siguiente al actual
     * @return El siguiente rango o el mismo si ya es el máximo
     */
    public Rank getNextRank() {
        int ordinal = this.ordinal();
        if (ordinal < Rank.values().length - 1) {
            return Rank.values()[ordinal + 1];
        }
        return this; // Si ya está en el rango máximo, devuelve el mismo
    }

    /**
     * Obtiene los puntos mínimos necesarios para este rango
     * @return Puntos mínimos para el rango
     */
    public int getMinPoints() {
        return minPoints;
    }

    /**
     * Obtiene los puntos máximos de este rango
     * @return Puntos máximos para el rango
     */
    public int getMaxPoints() {
        return maxPoints;
    }

    /**
     * Obtiene el nombre para mostrar de este rango
     * @return Nombre del rango
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Calcula cuántos puntos más se necesitan para alcanzar el siguiente rango
     * @param currentPoints Los puntos actuales del cliente
     * @return La cantidad de puntos adicionales necesarios, o 0 si ya está en el rango máximo
     */
    public int pointsToNextRank(int currentPoints) {
        Rank nextRank = getNextRank();
        if (nextRank == this) {
            return 0; // Ya está en el rango máximo
        }
        return Math.max(0, nextRank.minPoints - currentPoints);
    }

    @Override
    public String toString() {
        return displayName;
    }
}
