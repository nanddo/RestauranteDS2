

<?php

class ProdutoController extends BaseController {
    public function saveAction(){
        try {
            $produto = new Produto();
            $data = json_decode($this->request->get("data"), true);

            $produto->nome = $data["nome"];
            $produto->preco = $data["preco"];
            $ingredientesArr = $data["ingredientes"];

            if ($produto->save($data)){
            	foreach ($ingredientesArr as $ingredienteArr) {
            		$item = $ingredienteArr["item"];
            		$ingrediente = new Ingrediente();
            		$ingrediente->id_item = $item["id"];
            		$ingrediente->id_produto = $produto->id;
            		$ingrediente->quantidade = $ingredienteArr["quantidade"];
            		if (!$ingrediente->save()){
		            	$errors = [];
		                foreach ($ingrediente->getMessages() as $error) {
		                    $errors[] = $error->getMessage();
		                }
		                return ["success" => false, "errors" => $errors];		
            		}
            	}
                return ["id" => $produto->id];
            } else {
                $errors = [];
                foreach ($produto->getMessages() as $error) {
                    $errors[] = $error->getMessage();
                }
                return ["success" => false, "errors" => $errors];
            }
        } catch (Exception $e){
            print_r($e);
        }
    }

    public function findAction(){
        try {
            $produtos = Produto::find();
            $arr = [];
            foreach ($produtos as $produto){
                $ingredientes = $produto->getIngrediente();
                $ingredientes_arr = $ingredientes->toArray();
                for ($i = 0; $i < count($ingredientes_arr); $i++){
                    $ingredientes_arr[$i]["item"] = $ingredientes[$i]->getItem();
                }
                $produto = get_object_vars($produto);
                if ($ingredientes){
                    $produto["ingredientes"] = $ingredientes_arr;
                }
                $arr[] = $produto;
            }
            return ["data" => $arr];
        } catch (Exception $e){           
            print_r($e);
        }
        exit;
    }
}