<?php
error_reporting(E_ALL);
ini_set("display_errors", 1);
header('Content-Type: application/json');
include_once '../config/Database.php';
$json = json_decode(file_get_contents('php://input'), true);

$sqlQuery = 'SELECT username FROM users';
$recipesStatement = $bdd->prepare($sqlQuery);
$recipesStatement->execute();
$recipes = $recipesStatement->fetch(PDO::FETCH_OBJ);
$resultat = $bdd->query($sqlQuery);

while($colonne = $resultat->fetch(PDO::FETCH_OBJ)) 
{
    echo json_encode($colonne->username); 
}
?>