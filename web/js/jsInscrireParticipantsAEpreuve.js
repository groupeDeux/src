/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*Page javaScript pour l inscription des participants aux epreuves
 * Ctrler GetListEquipe/GetListSportifs/GetListEpreuve  <--> InscrireParticipantsAEpreuves.jsp*/
$("#selectionEpreuveEquipe").on('change', function () {
    alert();
    //recuperation de l'idEpreuve choisi
    var idEpreuve=document.getElementById("selectionEpreuveEquipe").value;
    $("#equipeInscrites").load("GetListEquipesInscrites?idEpreuve="+idEpreuve);
    
   //  document.location.href="GetListEquipesInscrites?idEpreuve="+idEpreuve;
});
$("#selectionEpreuveIndividuelle").on('click', function () {
    alert();
    //recuperation de l'idEpreuve choisi
    var idEquipe=document.getElementById("selectionEpreuveIndividuelle").value;
    
     document.location.href="GetListEquipesInscrites?idEpreuve="+idEquipe;
});