package sefakpsz.allInOnce.utils.constants;

public enum Messages {
    success("Operation Completed Successfully!"),
    error("Operation Has Failed!"),

    //auth
    wrong_password("Password Is Incorrect!"),
    unauthorized("Unauthorized!"),
    invalid_token("Invalid token!"),
    missing_token("Token Didn't provided!"),
    email_not_found("Email Couldn't Found!"),

    //user
    user_not_found("User Couldn't Found!"),

    //category
    category_not_found("Category Couldn't Found!"),
    category_already_exists("Category Already Exists!"),

    //product
    product_not_found("Product Couldn't Found!"),
    product_already_exists("Product Already Exists!"),

    //order
    order_not_found("Order Couldn't Found!"),
    order_already_exists("Order Already Exists!"),
    order_cant_update("Order Already Completed!"),
    not_blank("Field Has To Be Filled!");

    private final String text;

    Messages(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

}