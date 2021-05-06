class FooterClass{

    constructor(){
        this._footer = document.getElementById("foot");
        this._delete = document.getElementById("btnDelete");
        this._locale = "pt-BR";
        this._deleteDep =  document.getElementById("deleteDep");
         this._ancor ="${obj.id}";
        this.showDate();
      

    }
     
    get currentDate(){
        return new Date();
    }

    showDate(){
        this._footer.innerHTML =`&copy ${this.currentDate.toLocaleDateString(this._locale,{year:"numeric"})} Edvaldo Leite ` ;
    }
    

   myFunction(){
     this._deleteDep.addEventListener("click", e=>{ 
                                 this._delete.innerHTML =  
                                                                 `<a th:href="@{/departamentos/excluir/{id}(id = ${this._ancor} )}">
				                                                           <button type="button" class="btn btn-danger ml-3" id="btnExcluir">Sim, Excluir</button>
		                                                          </a>`
		console.log(this._delete);
     });
     }
     
    
}