<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

<xsl:template match="/">
  <html>
  <body>
  <h2>ID4JQM apply-template</h2>  
  <xsl:apply-templates/>  
  </body>
  </html>
</xsl:template>

<xsl:template match="class/student">
  <p>
    <xsl:apply-templates select="student"/>  
    <xsl:apply-templates select="vezeteknev"/>
    <xsl:apply-templates select="keresztnev"/>
    <xsl:apply-templates select="kor"/>
    <xsl:apply-templates select="fizetes"/>
  </p>
</xsl:template>

<xsl:template match="student">
	<br>
  ID: <span style="color:#5cff00">
  <xsl:value-of select="."/></span>
  </br>
</xsl:template>

<xsl:template match="vezeteknev">
	<br>
  Vezeteknev: <span style="color:#5cff00">
  <xsl:value-of select="."/></span>
  </br>
</xsl:template>

<xsl:template match="keresztnev">
	<br>
  Keresztnev: <span style="color:#ffa200">
  <xsl:value-of select="."/></span>
  </br>
</xsl:template>

<xsl:template match="kor">
	<br>
  Kor: <span style="color:#00cbff">
  <xsl:value-of select="."/></span>
  </br>
</xsl:template>

<xsl:template match="fizetes">
	<br>
  Fizetes: <span style="color:#ff0000">
  <xsl:value-of select="."/></span>
  </br>
</xsl:template>

</xsl:stylesheet>