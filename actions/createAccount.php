<?php

header('Content-Type: application/json');
include_once '../config/Database.php';
$json = json_decode(file_get_contents('php://input'), true);

if  (isset($json['username']) and isset($json['password'])) {
    $username = htmlspecialchars($json["username"]);
    $password = htmlspecialchars($json["password"]);
    $passwordhashed = password_hash($password, PASSWORD_DEFAULT);
    //echo $password;
    //$success="";

    if ($username == "" or $password = "") {
        $result["success"] = "";
        $result["error"] = "Le mot de passe et/ou le nom d'utilisateur n'est pas renseignes";
    } else {
        $checkifUsernameExists = $bdd->prepare('SELECT * FROM users WHERE username = ?');
        $checkifUsernameExists->execute(array($username));

        if ($checkifUsernameExists->rowCount() > 0) {
            $result["success"] = false;
            $result["error"] = "Cet identifiant a deja ete utilise !";
        } else {

            try {
                $createAccount = $bdd->prepare("INSERT INTO `users` (`id`, `username`, `userPassword`) VALUES (NULL, ?, ?);");
                $createAccount->execute(array($username, $passwordhashed));
                $result["success"] = true;
            } catch (Exception $e) {
                $result["success"] = false;
                $result["error"] = "erreur lors de la création du compte ... ! ";
            }
        }
    }
} else {
    $result["success"] = false;
    $result["error"] = "Veuillez completez tous les champs figurants ...";
}

echo json_encode($result);
?>