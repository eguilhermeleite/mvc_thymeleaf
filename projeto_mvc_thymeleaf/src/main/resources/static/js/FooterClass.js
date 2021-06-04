class FooterClass{

    constructor(){
        this._footer = document.getElementById("foot");
     
        this._locale = "pt-BR";
     
        this.showDate();
        
        this._btLinePerPage = document.getElementById("btLinePerPage");
        
        this._inLinePerPage = document.getElementById("inLinePerPage");
        
        this.lineInPage();
        
        this.showButton();
    }
     
    get currentDate(){
        return new Date();
    }

    showDate(){
        this._footer.innerHTML =`&copy ${this.currentDate.toLocaleDateString(this._locale,{year:"numeric"})} Edvaldo Leite ` ;
    }
    
    lineInPage(){
     this._btLinePerPage.addEventListener("click", e => {
      if(window.location.pathname == "/cargos/listarDesc" ){
           window.location.href = `/cargos/listarDesc?size=${this._inLinePerPage.value} `;
      }else{
        window.location.href = `/cargos/listar?size=${this._inLinePerPage.value} `; 
       }
      });
   }
   
   
 

  
}