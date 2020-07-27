## Random

```python
import random


random.seed(10) # 매번 동일한 결과를 반환해 주는 설정 (pseudo random)
random.random() # 0과 1사이의 난수를 생성
random.randrange(10) # 0~10 범위에서 난수 생성
random.randrange(3, 6) # 3~6 범위에서 난수 생성

ten = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
random.suffle(ten)	# 리스트의 항목을 임의 순서로 재정렬해 줌 (섞어줌)
random.choice(ten)	# 리스트에서 임의의 항목을 하나 선택
random.sample(ten, 6) # 리스트에서 중복이 허용되지 않는 임의의 표본 리스트를 생성
[random.choice(range(10)) for _ in range(4)] # 중복을 허용하는 표본 리스트
```

