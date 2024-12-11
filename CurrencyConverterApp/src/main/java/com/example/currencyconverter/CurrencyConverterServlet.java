package com.example.currencyconverter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/convert")
public class CurrencyConverterServlet extends HttpServlet {

    // Map to store predefined exchange rates
    private final Map<String, Double> exchangeRates = new HashMap<>();

    @Override
    public void init() throws ServletException {
        // Initialize exchange rates (example: USD to INR, EUR to INR, etc.)
        exchangeRates.put("USD_TO_INR", 82.5);
        exchangeRates.put("EUR_TO_INR", 90.0);
        exchangeRates.put("GBP_TO_INR", 103.2);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get form parameters
        String fromCurrency = request.getParameter("fromCurrency");
        String toCurrency = request.getParameter("toCurrency");
        String amountStr = request.getParameter("amount");

        // Debug logs
        System.out.println("Received request parameters: fromCurrency=" + fromCurrency +
                ", toCurrency=" + toCurrency + ", amount=" + amountStr);

        double amount = 0;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid amount format: " + amountStr);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Bad request
            response.getWriter().write("Invalid amount format.");
            return;
        }

        // Create the conversion key (e.g., "USD_TO_INR")
        String conversionKey = fromCurrency.toUpperCase() + "_TO_" + toCurrency.toUpperCase();
        Double rate = exchangeRates.get(conversionKey);

        // Set the response content type to HTML
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Check if the conversion rate exists and calculate the result
        if (rate != null) {
            // Perform the conversion
            double convertedAmount = amount * rate;

            // Return the result as HTML
            out.println("<h2>Converted Amount: " + convertedAmount + " " + toCurrency + "</h2>");
        } else {
            // If no valid conversion rate is found, display an error message
            out.println("<h3 style='color:red'>Invalid currency conversion request</h3>");
        }
    }
}
