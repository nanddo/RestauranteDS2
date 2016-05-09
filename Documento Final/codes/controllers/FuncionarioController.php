<?php

class FuncionarioController extends BaseController {
    public function saveAction(){
        $funcionario_arr = json_decode($this->request->get("data"), true);
        $endereco_arr = $funcionario_arr["endereco"];    
        $endereco = new Endereco();
        $funcionario = new Funcionario();

        if ($endereco->save($endereco_arr)){
            $funcionario->id_endereco = $endereco->id;
            if (!$funcionario->save($funcionario_arr)){
                $errors = [];
                foreach ($funcionario->getMessages() as $error) {
                    $errors[] = $error->getMessage();
                }
                return ["success" => false, "errors" => $errors];
            }
        } else {
            //return ["success" => false, "errors" => ["nada nao"]];
            $errors = [];
            foreach ($endereco->getMessages() as $error) {
                $errors[] = $error->getMessage();
            }
            return ["success" => false, "errors" => $errors];
        }

        return ["id" => $funcionario->id];

    }

    public function findAction(){
        try {
            $funcionarios = Funcionario::find();
            $arr = [];
            foreach ($funcionarios as $funcionario){
                $endereco = $funcionario->endereco;
                $funcionario = get_object_vars($funcionario);
                if ($endereco){
                    $funcionario["endereco"] = $endereco;
                }
                $arr[] = $funcionario;
            }
            return ["data" => $arr];
        } catch (Exception $e){           
            print_r($e);
        }
        exit;
    }
}