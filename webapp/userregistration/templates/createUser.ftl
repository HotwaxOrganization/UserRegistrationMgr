<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Create User</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 20px;
        }
        .form-container {
            max-width: 400px;
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
        input[type="text"] {
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
    <h2>Create User</h2>
    <form method="post" action="<@ofbizUrl>createPerson</@ofbizUrl>">
        <label>${uiLabelMap.FirstName}:</label>
        <input type="text" name="firstName" required>

        <label>${uiLabelMap.LastName}:</label>
        <input type="text" name="lastName" required>

        <label>${uiLabelMap.UserLoginId}:</label>
        <input type="text" name="userLoginId" required>


        <label>${uiLabelMap.CurrentPassword}:</label>
        <input type="text" name="currentPassword" required>

        <button type="submit">Create User</button>
    </form>
</div>

</body>
</html>