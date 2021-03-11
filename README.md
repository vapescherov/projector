# Car location projector

## Overview

Application finds the best projections of car locations on a race track. Race track represented as a polyline.

### Options

```
usage: java -jar projector.jar -r=FILE -l=FILE --gui

required arguments:
-r, --race=FILE         file with points of race track polyline
-l, --locations=FILE    file with locations of car

optional arguments:
--gui - draw race track and projections
```

### File format

Examples are available in the "example" folder of this repository

#### Race:

``
x_coordinate(double) y_coordinate(double)
``

Example:

```
386.0 63.5
590.0 73.5
840.0 190.5
768.5 253.0
514.5 116.5
442.5 424.5
651.0 538.0
```

#### Car locations

``
x_coordinate(double) y_coordinate(double) vector_length(double) vector_angle(double, [0..360])
``

Example:

```
386.0 63.5 0.0 0.0
428.0 95.0 30.0 0.0
501.0 111.5 90.0 10.0
553.0 75.5 90.0 3.0
625.0 70.0 90.0 33.4
658.5 103.5 90.0 26.4
731.5 128.0 90.0 1.2
```
