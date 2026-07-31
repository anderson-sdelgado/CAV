# Walkthrough - Refinamento da Seleção do Bafômetro (Toggle)

Refatorei a lógica de seleção na tela de Bafômetro para suportar o comportamento de "desmarcar" (toggle), garantindo que o estado retorne a `null` quando o usuário clica em uma opção já selecionada.

## Alterações Realizadas

### [presenter] - ViewModel

#### [CheckBreathalyzerViewModel.kt](file:///C:/Users/anderson/Documents/Kotlin/CAV/app/src/main/java/br/com/usinasantafe/cav/presenter/view/card/breathalyzer/check/CheckBreathalyzerViewModel.kt)
- **Lógica de Toggle**: As funções `onChangeFlagRealized` e `onChangeFlagResult` agora comparam o novo valor com o atual. Se forem iguais, o estado é definido como `null`.
- **Limpeza em Cadeia**: Ao desmarcar a realização do bafômetro (`flagRealized` se tornando `null`), o resultado (`flagResult`) também é automaticamente resetado para `null`.

## Verificação

As alterações foram validadas logicamente para garantir que:
1. Se "NÃO" estiver marcado e for clicado novamente, ele desmarca e fica tudo limpo.
2. Se "SIM" estiver marcado com um resultado (ex: "POSITIVO") e o usuário clicar em "SIM" de novo, tanto a realização quanto o resultado são limpos.
3. O grupo de resultado ("NEGATIVO/POSITIVO") agora permite desmarcar a opção selecionada, deixando o grupo sem nenhuma marcação, conforme solicitado.
