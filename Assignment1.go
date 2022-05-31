package main

import (
	"bufio"
	"crypto/sha256"
	"fmt"
	"os"
	"strconv"
	"strings"
	"time"
)

func main() {
	scan := bufio.NewScanner(os.Stdin)
	fmt.Println("Enter the text:-")
	scan.Scan()
	input := scan.Text() //scanning the text from the user
	i := 1
	o := input + strconv.Itoa(i)
	hash := sha256.Sum256([]byte(o)) //converting to the hash

	target := "00000fffffffffffffffffffffffffffffffffffffffffffffffffffffffffff"
	hexstring := fmt.Sprintf("%x", hash) //converting the byte array to hexadecimal string
	start := time.Now()
	for ; strings.Compare(target, hexstring) != 1; i++ {
		o := input + strconv.Itoa(i) //appending numbers till nonce is obtained
		hash = sha256.Sum256([]byte(o))
		hexstring = fmt.Sprintf("%x", hash)
	}
	elapsed := time.Since(start)
	fmt.Println(hexstring)
	fmt.Printf("Nonce : %d\nTime : %v", i-1, elapsed)
}
