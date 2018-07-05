# text-transformer-util

## Build
``` console
$ mvn clean install
```

## Unit Tests
``` console
$ mvn test
```

## Running the application from an IDE

* Application Entry point
    - com.transformer.Main
    
* Program Arguments
    - ./input.txt ./transformations.txt
    
    
## Running the application via a jar
```console
# Build
$ mvn clean package

# Run 
java -jar target/text-transformer-util-1.0-SNAPSHOT.jar /path/to/input.txt /path/to/transformations.txt
```

## Sample output

```
$ java -jar target/text-transformer-util-1.0-SNAPSHOT.jar /path/to/input.txt /path/to/transformations.txt

Input and transformation files will be picked up from command line arguments
--------------------------------------------
Original Text          : Lorem ipsum dolor sit er elit lamet, consectetaur cillium adipisicing pecu, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.Nam liber te conscient to factor tum poen legum odioque civiuda.
Transformation applied : H,V,H,15,H,V,-12,V,H,V,V,H,H,H,-100,58,H
Transformed Text       : 8iexn uoayn si8ie aur xe x8ur 8/nxrm wibaxwrxr/ye wu88uyn /suouauwub4 oxwym axs si xuyanis rxnoie ubwususybr yr 8/viex xr si8iex n/4b/ /8u0y/, yr xbun /s nubun cxbu/nm 0yua biareys xqxewur/ruib y88/nwi 8/vieua buau yr /8u0yuo xq x/ winnisi wibax0y/r, syua /yrx ueyex si8ie ub exoex5xbsxeur ub ci8yor/rx cx8ur xaax wu88yn si8iex xy 3y4u/r by88/ o/eu/rye, xqwxorxye aubr iww/xw/r wyous/r/r bib oeiusxbrm aybr ub wy8o/ 0yu i33uwu/ sxaxeybr ni88ur /bun us xar 8/vieyn,b/n 8uvxe rx wibawuxbr ri 3/wrie ryn oixb 8x4yn isui0yx wucuys/,
--------------------------------------------

Process finished with exit code 0
```