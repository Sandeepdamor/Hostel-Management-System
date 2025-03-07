<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Error 500</title>
    <style>
        body {
            background-color: rgb(51, 51, 51);
            width: 100vw;
            height: 100vh;
            color: white;
            font-family: 'Arial Black', sans-serif;
            text-align: center;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
        .error-num {
            font-size: 8em;
        }
        .eye {
            background: #fff;
            border-radius: 50%;
            display: inline-block;
            height: 100px;
            position: relative;
            width: 100px;
            margin: 0 10px;
            overflow: hidden;
        }
        .pupil {
            background: #000;
            border-radius: 50%;
            height: 33px;
            width: 33px;
            position: absolute;
            left: 50%;
            top: 50%;
            transform: translate(-50%, -50%);
            transition: transform 0.05s linear;
        }
        p {
            margin-bottom: 4em;
        }
        a {
            color: white;
            text-decoration: none;
            text-transform: uppercase;
        }
        a:hover {
            color: lightgray;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $(document).mousemove(function(event) {
                $('.eye').each(function() {
                    var eye = $(this);
                    var pupil = eye.find('.pupil');
                    var x = eye.offset().left + eye.width() / 2;
                    var y = eye.offset().top + eye.height() / 2;
                    var dx = event.pageX - x;
                    var dy = event.pageY - y;
                    var dist = Math.sqrt(dx * dx + dy * dy);
                    var maxDist = 20;
                    var moveX = (dx / dist) * Math.min(maxDist, dist);
                    var moveY = (dy / dist) * Math.min(maxDist, dist);
                    pupil.css('transform', 'translate(calc(-50% + ' + moveX + 'px), calc(-50% + ' + moveY + 'px))');
                });
            });
        });
    </script>
</head>
<body>
    <div>
        <span class='error-num'>5</span>
        <div class='eye'><div class='pupil'></div></div>
        <div class='eye'><div class='pupil'></div></div>
        <p class='sub-text'>Oh eyeballs! Something went wrong. We're <i>looking</i> to see what happened.</p>
        <a href="./">Go back</a>
    </div>
</body>
</html>
