### H264 存储传输格式: Annex B / AVCC

```
NALU is basic unit.

Then,

annexb format:

([start code] NALU) | ( [start code] NALU) |

avcc format:

([extradata]) | ([length] NALU) | ([length] NALU) |
```

In annexb, [start code] may be 0x000001 or 0x00000001.

In avcc, the bytes of [length] depends on NALULengthSizeMinusOne in avcc extradata, the value of [length] depends on the size of following NALU and in both annexb and avcc format, the NALUs are no different.

### Annex B

NALU/NAL: Network Abstraction Layer Units  
开始码 00 00 00 01 / 00 00 01
Nalu 第一个字节:  
bit0 通常为0  
bit1-bit2 为0表示没有被别的 nalu 引用，否则被别的 nalu 引用，值越大越重要  
bit3-bit7 表示 nalu type:  

| 0 | Unspecified | non-VCL |
|---|---|---|
|1|Coded slice of a non-IDR picture|VCL|
|2|Coded slice data partition A|VCL|
|3|Coded slice data partition B|VCL|
|4|Coded slice data partition C|VCL|
|5|Coded slice of an IDR picture|VCL|
|6|Supplemental enhancement information (SEI)|non-VCL|
|7|Sequence parameter set|non-VCL|
|8|Picture parameter set|non-VCL|
|9|Access unit delimiter|non-VCL|
|10|End of sequence|non-VCL|
|11|End of stream|non-VCL|
|12|Filler data|non-VCL|
|13|Sequence parameter set extension|non-VCL|
|14|Prefix NAL unit|non-VCL|
|15|Subset sequence parameter set|non-VCL|
|16|Depth parameter set|non-VCL|
|17..18|Reserved|non-VCL|
|19|Coded slice of an auxiliary coded picture without partitioning|non-VCL|
|20|Coded slice extension|non-VCL|
|21|Coded slice extension for depth view components|non-VCL|
|22..23|Reserved|non-VCL|
|24..31|Unspecified|non-VCL|
