<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body>
<div class="container">
    <div class="blueBg">
        <div class="box signin">
            <h2>Already have an account ?</h2>
            <button class="signinBtn">Sign in</button>
        </div>
        <div class="box signup">
            <h2>Don't have an account ?</h2>
            <button class="signupBtn"><a href="/register" style="text-decoration: none;">Registration</a>
            </button>
        </div>
    </div>
    <div class="formBox">
        <div class="form signinForm">
            <form method="post">
                <h3>Sign In</h3>
                <input type="text" name="email" id="signIn" placeholder="Username" required>
                <input type="password" name="password" id="password" placeholder="Password" required>
                <input type="submit" value="LOGIN">
                ${message!''}
            </form>
        </div>
    </div>
</div>
</body>
</html>