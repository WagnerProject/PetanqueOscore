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

?>
          <?php
          //On démarre une nouvelle session
          session_start();
          
          //On définit des variables de session
       
          
      ?>
      <html>
   <form name="inscription" method="post" action="Stats.php">
   <?php
if (isset($_SESSION['pseudo']))
{
?>
        <input type="text" name="pseudo" value="<?php echo $_SESSION['pseudo']; ?>" class="input" disabled />
<?php
}

else
{
?>           
        <input type="text" name="pseudo" value="Pseudo" class="input"/>
<?php
}
?>
   <input type="submit" value="Envoyer" />
   </form>
            </html>

            <?php
             if (isset ($_POST['Envoyer'])){
              $_SESSION['prenom']=$pseudo=$_POST['pseudo'];
              $createpseudo = $pdo->prepare("INSERT INTO `Resultats` (`pseudo`, `Partenaire1`, `Partenaire2`, `Adversaire1`, `Adversaire2`, `Partie_jouer`, `Partie_Gagne`, `Partie_perdu`, `Score`) VALUES ('$pseudo', '', '', '', '', '', '', '', '')");
                $createpseudo->execute(array()); }
        
            ?>