<?php 

error_reporting(E_ALL);
ini_set("display_errors", 1);

$host = "bastienfkmpetanq.mysql.db";
$user = "bastienfkmpetanq";
$pass = "Petanque33";
$dbname ="bastienfkmpetanq";

try {
    $dsn = "mysql:host=" . $host . ";dbname=" . $dbname;
    $pdo = new PDO($dsn, $user, $pass);
    $pdo->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_OBJ);
  } catch(PDOException $e) {
    echo "DB Connection Failed: " . $e->getMessage();
  }

  $stmt = $pdo->query('SELECT pseudo,Partie_Gagne,Partie_perdu,Partie_jouer FROM `Resultats`');  
?>
<!DOCTYPE html>
<html lang="fr">
<head>
    <link rel ="stylesheet" href ="Styles.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <table class="table">
    <thead>

    <tr>
                <th>Pseudo</th>
                <th>Partie Gagner</th>
                <th>Partie Perdue</th>
                <th>Partie Jouer</th>
                <th>Pourcentage victoire</th>

    <tr>
    
    </thead>

    <tbody>
    
    <?php    
    
    while($row = $stmt->fetch())
                {?>                <tr>
                                    <td><?php echo $row->pseudo;?></td>
                                    <td><?php echo $row->Partie_Gagne;?></td>
                                    <td><?php echo $row->Partie_perdu;?> </td>
                                    <td><?php echo $row->Partie_jouer;?> </td>
                                    <td><?php echo $row->Partie_Gagne*100/$row->Partie_jouer;?></td>
                                        </tr>
                        <?php } ?>  
    </tbody>
    
    </table>
</body>
</html>



