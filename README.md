# calculator
简单计算器

## feature
- 支持优先级四则运算
- 支持undo，redo

## 使用方式

```aidl
        BigDecimal res = new Calculator().add(new BigDecimal("1.1"))
                .sub(new BigDecimal("2"))
                .mul(new BigDecimal("3"))
                .add(new BigDecimal("1"))
                .div(new BigDecimal("1"))
                .calc();
```