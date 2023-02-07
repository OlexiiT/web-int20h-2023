import Form from "@/components/forms/form";
import TextField from "@/components/forms/inputs/text-field";
import SubmitButton from "@/components/forms/submit-button";
import TextLink from "@/components/navigation/text-link";
import Link from "next/link";

export default function Login() {
  return (
    <Form captionText="Login" className="items-center">
      <TextField label="Username" name="username" />
      <TextField label="Password" name="password" type="password" />
      <TextLink href="/register">I don't have an account</TextLink>
      <SubmitButton label="Login" />
    </Form>
  );
}
