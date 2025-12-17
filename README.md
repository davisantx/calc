# Calc

Calculadora de expressões numéricas no terminal.

## Como instalar e executar?

```
git clone https://github.com/davisantx/calc.git

cd calc

mvn package

java -cp target/calc-1.0-SNAPSHOT.jar davisantx.Main
```

## Como usar?

### Operadores

- (+) Soma
- (-) Subtração
- (*) Multiplicação
- (/) Divisão
- (^) Exponenciação

### Calcular
Insira a expressão quando aparecer o:

```
>
```

```
> 2 ^ 3 + 1
```

### Sair

Para sair:

```
> exit
```

## Limitações

1. Não suporta (), {}, []
2. Não suporta a precedência correta de operações, efetuando-as linearmente, em sequência.