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

    private final Map<String, Double> exchangeRates = new HashMap<>();

    @Override
    public void init() throws ServletException {
        exchangeRates.put("USD_TO_INR", 82.5);
        exchangeRates.put("EUR_TO_INR", 90.0);
        exchangeRates.put("GBP_TO_INR", 103.2);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String fromCurrency = request.getParameter("fromCurrency");
        String toCurrency = request.getParameter("toCurrency");
        String amountStr = request.getParameter("amount");

        double amount = 0;
        try {
            amount = Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid amount format.");
            return;
        }

        String conversionKey = fromCurrency.toUpperCase() + "_TO_" + toCurrency.toUpperCase();
        Double rate = exchangeRates.get(conversionKey);

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        if (rate != null) {
            double convertedAmount = amount * rate;
            out.println("<h2>Converted Amount: " + convertedAmount + " " + toCurrency + "</h2>");
        } else {
            out.println("<h3 style='color:red'>Invalid currency conversion request</h3>");
        }
    }
}
