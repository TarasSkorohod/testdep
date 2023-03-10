<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register</title>
    <link rel="stylesheet" href="/static/css/styles.css">
</head>
<body class="active">
<div class="container">
    <div class="blueBg">
        <div class="box signin">
            <h2>Already have an account ?</h2>
            <button class="signinBtn"><a href="/login" style="text-decoration: none;">Sign in</a></button>
        </div>
        <div class="box signup">
            <h2>Don't have an account ?</h2>
            <button class="signupBtn">Sign up</button>
        </div>
    </div>
    <div class="formBox active">

        <div class="form signupForm">
            <form method="post">
                <h3>Sign Up</h3>

                <input type="text" name="firstname" id="signIn" placeholder="Firstname" required>
                <input type="text" name="lastname" id="signIn" placeholder="Lastname" required>
                <input type="text" name="age" id="signIn" placeholder="Age" required>
                <input type="email" name="email" id="email" placeholder="Email" required>
                <input type="password" name="password" id="password" placeholder="Password" required>
                <input type="password" name="passwordConf" id="passwordConf" placeholder="Confirm Password" required>
                <input type="text" name="url" id="signIn" placeholder="Picture URL" required>
                <input type="submit" value="REGISTER">
                ${message!''}
            </form>
        </div>
    </div>
</div>


</body>
</html>