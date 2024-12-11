<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Currency Conversion Result</title>
</head>
<body>
    <h2>Currency Conversion Result</h2>
    <p>
        <c:if test="${not empty convertedAmount}">
            Converted Amount: ${convertedAmount}
        </c:if>
        <c:if test="${not empty errorMessage}">
            <h3 style="color:red">${errorMessage}</h3>
        </c:if>
    </p>
    <a href="index.html">Go Back</a>
</body>
</html>
