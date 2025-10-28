# ROTAS

## CONSULTA DE REGISTROS 
*ROTAS GET*
  
Lista todos os registros
- `http://localhost:8080/api/tipos_combustivel`  
- `http://localhost:8080/api/bombas_combustivel`  
- `http://localhost:8080/api/abastecimentos`

Lista registros por ID
- `http://localhost:8080/api/tipos_combustivel`  
- `http://localhost:8080/api/bombas_combustivel`  
- `http://localhost:8080/api/abastecimentos`  

---

## INSERÇÃO DE NOVOS REGISTROS
**ROTAS POST**
- `http://localhost:8080/api/tipos_combustivel`
```json
    {
        "nome": "etanol",
        "precoPorLitro": 5.55
    }
```

- `http://localhost:8080/api/bombas_combustivel`
```bash
    {
      "nome": "Bomba 2",
      "tipoCombustivel": {
        "id": 2 // id tipo de combustivel
      }
    }
```

- `http://localhost:8080/api/abastecimentos`
```bash
    {
      "bomba": {
        "id": 1 // id bomba de combustivel
      },
      "dataAbastecimento": "2025-10-27T18:30:00",
      "litrosAbastecidos": 45.50,
      "valorTotal": 270.00
    }
```

---

## ATUALIZAÇÃO DE REGISTROS
*ROTAS PUT*
- `http://localhost:8080/api/abastecimentos/1`
```bash
    {
      "bomba": {
        "id": 2
      },
      "dataAbastecimento": "2025-10-27T18:30:00",
      "litrosAbastecidos": 47.50,
      "valorTotal": 270.00
    }   
```

- `http://localhost:8080/api/bombas_combustivel/1`
```bash
  {
    "nome": "Bomba B - Atualizada",
    "tipoCombustivel": {
      "id": 1
    }
  }   
```

- `http://localhost:8080/api/tipos_combustivel/1`
```bash
    {
        "nome": "Etanol",
        "precoPorLitro": 5.56
    }  
```

---

## APAGAR REGISTROS DO BANCO
**ROTAS DELETE**  
- `http://localhost:8080/api/tipos_combustivel/1`  
- `http://localhost:8080/api/bombas_combustivel/1`  
- `http://localhost:8080/api/abastecimentos/1`  