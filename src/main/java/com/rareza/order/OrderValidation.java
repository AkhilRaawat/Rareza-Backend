package com.rareza.order;

public class OrderValidation {
    /**
     * Throws IllegalArgumentException if both design and uploadedImage are null or both not null.
     */
    public static void checkDesignOrImageNotNull(Object design, Object uploadedImage) {
        if ((design == null && uploadedImage == null) || (design != null && uploadedImage != null)) {
            throw new IllegalArgumentException("Exactly one of design or uploaded image must be provided");
        }
    }
}

