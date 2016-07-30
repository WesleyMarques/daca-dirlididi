package bootwildfly.controllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.*;

@RestController
public class ProblemController {

	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "start", value = "The start of pagination", 
        	required = true, dataType = "int", paramType = "query"),
        @ApiImplicitParam(
            name = "max", value = "The size of pagination", required = true, 
            dataType = "int", paramType = "query"),
        @ApiImplicitParam(
            	name = "order_col", value = "The column to be ordered", required = true, 
            	dataType = "string", paramType = "query"),
        @ApiImplicitParam(
            	name = "order_type", value = "The type of ordernation (asc|desc)", required = true, 
            	dataType = "string", paramType = "query")
      })
	@RequestMapping(method = RequestMethod.GET, path="/problem", produces = "application/json")
    public String get(){
        return ("[{name: “problema 1”,desc : “descricao ... “,code: “ky34hke9”,created_at: “30/07/2016”,solved: “true”}]");
    }
	
	@RequestMapping(method = RequestMethod.GET, path="/problem/{code}", produces = "application/json")
    public String show(@PathVariable("code") String code){
        return ("{name: “problema 1”,desc : “descricao ... “,code: “ky34hke9”,dica: “dica para o prob...”,testes : [{descricao : “teste 1”,dica : “dica 1”,entrada : “entrada 1”,saida : “saida 1”}]}");
    }
	
	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "Problem's name", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "desc", value = "Problem's description", required = true, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tip", value = "Problem's typ", required = true, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tests", value = "Problem's tests", required = false, 
            	dataType = "array of objects {}", paramType = "body", 
            	defaultValue = "[{desc: “teste 1”,tip: “dica 1”,input: “entrada 1”,output: “saida 1”}]")
      })	
	@RequestMapping(method = RequestMethod.POST, path="/problem", produces = "application/json")
    public String save(){
        return ("{message : “Problem created successfully”}");
    }
	
	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "name", value = "New problem's name", required = false, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "desc", value = "New problem's description", required = false, dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tip", value = "New problem's typ", required = false, 
            	dataType = "string", paramType = "body"),
        @ApiImplicitParam(
            	name = "tests", value = "New problem's test(s)", required = false, 
            	dataType = "array of objects {}", paramType = "body", 
            	defaultValue = "[{desc: “teste 1”,tip: “dica 1”,input: “entrada 1”,output: “saida 1”}]")
      })	
	@RequestMapping(method = RequestMethod.PUT, path="/problem/{code}", produces = "application/json")
    public String update(@PathVariable("code") String code){
        return ("{message : “Problem edited successfully”}");
    }
	
	@ApiImplicitParams({
        @ApiImplicitParam(
        	name = "status", value = "Problem's status (solved|close)", required = true, 
        	dataType = "string", paramType = "body")
      })	
	@RequestMapping(method = RequestMethod.POST, path="/problem/{code}/status", produces = "application/json")
    public String setStatus(@PathVariable("code") String code){
        return ("{message : “The problem was marked as solved|closed”}");
    }
}