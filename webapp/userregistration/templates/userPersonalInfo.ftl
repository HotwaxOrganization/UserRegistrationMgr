<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Personal Information</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .form-container {
            max-width: 600px;
            margin: auto;
            background: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #555;
        }
        input[type="text"],
        input[type="email"],
        textarea {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }
        button {
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            color: white;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>

<div class="form-container">
    <form method="post" action="<@ofbizUrl>userPersonalInfo</@ofbizUrl>">
        <input type="hidden" name="partyId" value="${requestAttributes.partyId!}">

        <h2>User Personal Information</h2>

        <label>${uiLabelMap.FirstName}:</label>
        <input type="text" name="firstName" value="${requestAttributes.firstName!}" required>

        <label>${uiLabelMap.LastName}:</label>
        <input type="text" name="lastName" value="${requestAttributes.lastName!}" required>

        <label>${uiLabelMap.EmailAddress}:</label>
        <input type="email" name="emailAddress" required>

        <label>${uiLabelMap.PhoneNumber}:</label>
        <input type="text" name="phoneNumber">

        <label>${uiLabelMap.MobileNumber}:</label>
        <input type="text" name="mobileNumber">

        <h3>Billing Address</h3>
        <label>Street Address:</label>
        <input type="text" name="bAddress" required>

        <label>City:</label>
        <input type="text" name="bCity" required>

        <label>Postal Code:</label>
        <input type="text" name="bPostalCode" required>

        <h3>Shipping Address</h3>
        <label>Street Address:</label>
        <input type="text" name="sAddress" required>

        <label>City:</label>
        <input type="text" name="sCity" required>

        <label>Postal Code:</label>
        <input type="text" name="sPostalCode" required>

        <button type="submit">Submit</button>
    </form>
</div>

</body>
</html>
