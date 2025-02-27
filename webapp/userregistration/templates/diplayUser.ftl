<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Customer Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .details-container {
            max-width: 600px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            text-align: center;
            color: #333;
            font-size: 2.5em; /* Increased font size for the heading */
        }
        p {
            margin: 10px 0;
            color: #555;
            font-size: 1.2em; /* Increased font size for paragraphs */
        }
        strong {
            color: #333;
            font-size: 1.2em; /* Increased font size for strong text */
        }
        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 1.2em; /* Increased font size for button text */
            margin-top: 20px;
        }
        button:hover {
            background-color: #4cae4c;
        }


    </style>
</head>
<body>

<div class="details-container">
    <h1>Customer Details</h1>

    <p><strong>${uiLabelMap.PartyId}:</strong> ${context.partyId!}</p>
    <p><strong>${uiLabelMap.FirstName}:</strong> ${context.firstName!}</p>
    <p><strong>${uiLabelMap.LastName}:</strong> ${context.lastName!}</p>
    <p><strong>${uiLabelMap.EmailAddress}:</strong> ${context.emailAddress!}</p>
    <p><strong>${uiLabelMap.HomeNumber}:</strong> ${context.homeNumber!}</p>
    <p><strong>${uiLabelMap.MobileNumber}:</strong> ${context.mobileNumber!}</p>

    <h2>Billing Address</h2>
    <p><strong>Street Address:</strong> ${context.billingAddress!}</p>
    <p><strong>City:</strong> ${context.bCity!}</p>
    <p><strong>Postal Code:</strong> ${context.bPostalCode!}</p>

    <h2>Shipping Address</h2>
    <p><strong>Street Address:</strong> ${context.shippingAddress!}</p>
    <p><strong>City:</strong> ${context.sCity!}</p>
    <p><strong>Postal Code:</strong> ${context.sPostalCode!}</p>

    <br>
    <a href="<@ofbizUrl>main</@ofbizUrl>"><button>Finish</button></a>
</div>

</body>
</html>
