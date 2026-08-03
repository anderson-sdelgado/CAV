# Walkthrough - Correção de Digitação Decimal (Bafômetro)

Corrigi as funções utilitárias de teclado numérico para suportar dinamicamente o número de casas decimais, resolvendo o problema de deslocamento da vírgula durante a digitação de valores para o bafômetro.

## Alterações Realizadas

### [theme] - Lógica de Teclado

- **[Buttons.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/main/java/br/com/usinasantafe/cav/presenter/theme/Buttons.kt)**:
    - Refatorei `addTextFieldComma` e `clearTextFieldComma` para utilizar `10.0.pow(decimalPlaces)` no cálculo do divisor.
    - Isso garante que, se o campo for configurado para 2 casas decimais, o valor digitado seja dividido por 100,0, transformando o primeiro dígito "1" corretamente em "0,01" em vez de "0,10".

### [presenter] - ViewModel

- **[CountBreathalyzerViewModel.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/main/java/br/com/usinasantafe/cav/presenter/view/card/breathalyzer/count/CountBreathalyzerViewModel.kt)**:
    - Atualizei a chamada de `clearTextFieldComma` para passar a constante `COUNT_DECIMAL` (2).
    - Isso corrige o comportamento de "apagar", garantindo que a vírgula permaneça na posição correta (2 casas) enquanto o usuário remove dígitos.

## Verificação

As alterações garantem que o comportamento de digitação estilo "caixa eletrônico" funcione para qualquer número de casas decimais informado:
1. Com 2 casas: Digitou "1" -> "0,01". Digitou "2" -> "0,12". Digitou "5" -> "1,25".
2. Apagar: "1,25" -> "APAGAR" -> "0,12".
